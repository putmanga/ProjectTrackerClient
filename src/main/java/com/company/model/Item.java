package com.company.model;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Item {
    private String createdDate;
    private String description;
    private Integer id;
    private String itemStatus;
    private String itemType;
    private String title;
    private User user;

}
