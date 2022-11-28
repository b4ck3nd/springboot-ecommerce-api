package com.kodlamiyoruz.ecomm.service.user;


import com.kodlamiyoruz.ecomm.converter.*;
import com.kodlamiyoruz.ecomm.dto.address.AddressResponseDto;
import com.kodlamiyoruz.ecomm.dto.order.OrderResponseDto;
import com.kodlamiyoruz.ecomm.dto.product.comment.ProductCommentResponseDto;
import com.kodlamiyoruz.ecomm.dto.user.*;
import com.kodlamiyoruz.ecomm.exception.BadRequestException;
import com.kodlamiyoruz.ecomm.exception.CustomUserException;
import com.kodlamiyoruz.ecomm.exception.NotFoundException;
import com.kodlamiyoruz.ecomm.exception.SellerException;
import com.kodlamiyoruz.ecomm.model.*;
import com.kodlamiyoruz.ecomm.repository.CreditCardRepository;
import com.kodlamiyoruz.ecomm.repository.SellerRepository;
import com.kodlamiyoruz.ecomm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserConverter userConverter;

    @Autowired
    ProductCommentConverter commentConverter;

    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    CreditCardConverter creditCardConverter;

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    OrderConverter orderConverter;

    @Autowired
    AddressConverter addressConverter;

    @Transactional
    @Override
    public void add(UserCreateRequestDto dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new CustomUserException(dto.getUserName(),dto.getEmail());
        }

        User user=userConverter.userCreateRequestDtoDoUser(dto);
        userRepository.save(user);
    }

    @Override
    public List<UserResponseDto> findAll() {
        return userConverter.userListToToUserResponseDtoList(userRepository.findAll());
    }


    @Override
    public UserResponseDto findById(int id) {
        Optional<User> get = userRepository.findById(id);
        if (!(get.isPresent())) {
            throw new CustomUserException(id);
        }
        return userConverter.userToUserResponseDto(get.get());
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        if (!(userRepository.existsById(id))) {
            throw new CustomUserException(id);
        }
        userRepository.deleteById(id);
    }

    @Override
    public UserResponseDto findByUserName(String userName) {
        User byUserName = userRepository.findByUserName(userName);
        if (Objects.isNull(byUserName)) {
            throw new CustomUserException(userName);
        }

        return userConverter.userToUserResponseDto(byUserName);
    }

    @Override
    public UserResponseDto findByEmail(String email) {
        User byEmail = userRepository.findByEmail(email);
        if (Objects.isNull(byEmail)) {
            throw new CustomUserException(email);
        }

        return userConverter.userToUserResponseDto(byEmail);
    }

    @Transactional
    @Override
    public UserResponseDto updateByUserId(UserUpdateRequestDto dto) {
        if ( !( userRepository.existsById(dto.getUserId()))) {
            throw new CustomUserException(dto.getUserId());
        }
        User user=userConverter.userUpdateRequestDtoToUser(dto);
        userRepository.save(user);
        return userConverter.userToUserResponseDto(user);
    }

    @Override
    public List<ProductCommentResponseDto> findProductCommentsByUserId(int id) {
        if (!userRepository.existsById(id)) {
            throw new CustomUserException(id);
        }
        User user = userRepository.findById(id).get();
        List<ProductComment> productComment = user.getProductComment();
        return  commentConverter.productCommentListToProductCommentDtoList(productComment);
    }

    @Transactional
    @Override
    public void addCreditCard(CreditCardCreateRequestDto dto) {
        if (!userRepository.existsById(dto.getUserId())) {
            throw new CustomUserException(dto.getUserId());
        }
        if (dto.getExpirationMonth()<new Date().getMonth()) {
            throw new NotFoundException("month must be greater than or equal to " + new Date().getMonth());
        }
        if (dto.getExpirationYear() < new Date().getYear()) {
            throw new NotFoundException("year must be greater than or equal to " + new Date().getYear());
        }
        CreditCard card = creditCardConverter.creditCardCreateRequestDtoDoCreditCard(dto);
        User user = userRepository.findById(dto.getUserId()).get();
        card.setUser(user);
        creditCardRepository.save(card);
        List<CreditCard> creditCardList= new ArrayList<>();
        creditCardList.add(card);
        user.setCreditCards(creditCardList);
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void deleteCreditCardById(int id) {
        if (!creditCardRepository.existsById(id)) {
            throw new NotFoundException();
        }
        creditCardRepository.deleteById(id);
    }

    @Override
    public List<CreditCardResponseDto> findAllCreditCardByUserId(int id) {
        if (!userRepository.existsById(id)) {
            throw new CustomUserException(id);
        }
        List<CreditCard> creditCards = userRepository.findById(id).get().getCreditCards();
        return creditCardConverter.creditCartListToCreditCardDtoList(creditCards);
    }

    @Override
    public CreditCardResponseDto findCreditCardByUserIdAndCreditCardId(int userId, int creditCardId) {
        if (!userRepository.existsById(userId)) {
            throw new CustomUserException(userId);
        }
        if (!creditCardRepository.existsById(creditCardId)) {
            throw new NotFoundException(creditCardId);
        }
        String creditCardEmail = creditCardRepository.findById(creditCardId).get().getUser().getEmail();
        String userEmail = userRepository.findById(userId).get().getEmail();
        if (!userEmail.equals(creditCardEmail)) {
            throw new BadRequestException();
        }
        CreditCard card = creditCardRepository.findById(creditCardId).get();
        return creditCardConverter.creditCardToCreditCardResponseDto(card);

    }

    @Transactional
    @Override
    public void followSellerBySellerId(UserFollowSellerRequestDto dto) {
        if (!userRepository.existsById(dto.getUserId())) {
            throw new CustomUserException(dto.getUserId());
        }
        if (!sellerRepository.existsById(dto.getSellerId())) {
            throw new SellerException(dto.getSellerId());
        }
        User user = userRepository.findById(dto.getUserId()).get();
        Seller seller = sellerRepository.findById(dto.getSellerId()).get();
        List<Seller> sellerList=new ArrayList<>();
        sellerList.add(seller);
        List<User> userList=new ArrayList<>();
        userList.add(user);
        seller.setFollowers(userList);
        user.setFollowing(sellerList);
        userRepository.save(user);
        sellerRepository.save(seller);

    }

    @Override
    public List<UserFollowingSellerResponseDto> followingSeller(int id) {
        if (!userRepository.existsById(id)) {
            throw new CustomUserException(id);
        }
        List<Seller> following = userRepository.findById(id).get().getFollowing();
        return  userConverter.sellerListToUserFollowingSellerResponseDtoList(following);
    }

    @Transactional

    @Override
    public void removeFollowingSeller(int userId, int sellerId) {
        if (!userRepository.existsById(userId)) {
            throw new CustomUserException(userId);
        }
        if (!sellerRepository.existsById(sellerId)) {
            throw new SellerException(sellerId);
        }
        User user = userRepository.findById(userId).get();
        Seller seller = sellerRepository.findById(sellerId).get();
        seller.getFollowers().remove(user);
        user.getFollowing().remove(seller);
        userRepository.save(user);
        sellerRepository.save(seller);
    }

    @Override
    public List<OrderResponseDto> findAllOrdersByUserId(int id) {
        List<Order> orders = userRepository.findById(id).get().getOrders();
        return orderConverter.orderListToOrderResponseDtoList(orders);
    }




    @PostConstruct
    private void test() {
        User user=new User();
        user.setUserName("test isimli kisi");
        user.setEmail("testisimlikisi@kisi.com");
        user.setPassword("passwordeneymisbe");
        userRepository.save(user);

    }




}
