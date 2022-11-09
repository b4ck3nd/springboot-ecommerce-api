package com.kodlamiyoruz.ecomm.model;



import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "PRODUCT_COMMENT")
@ApiModel(value = "ProductComment models",description = "Model")
public class ProductComment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "unique identifier of productcomment object",allowableValues = "Integer")
    @Column(name = "product_comment_id")
    private int id;

    @Column(name = "title")
    @ApiModelProperty(notes = "title field of productcomment object",allowableValues = "String")
    private String title;

    @Column(name = "body")
    @ApiModelProperty(notes = "body field of productcomment object",allowableValues = "String")
    private String body;

    @Column(name = "rating")
    @ApiModelProperty(notes = "rating field of productcomment object",allowableValues = "Integer")
    private int rating;

    @Column(name = "created_date")
    @CreationTimestamp
    @ApiModelProperty(notes = "createddate field ob productcomment object",allowableValues = "Date")
    private Date createdDate;

    public ProductComment() {
        this.createdDate=new Date();
    }

    public ProductComment(String title,String body,int rating) {
        this.title=title;
        this.body=body;
        this.rating=rating;
        this.createdDate=new Date();
    }

}
