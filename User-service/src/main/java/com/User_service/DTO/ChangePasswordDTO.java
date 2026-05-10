package com.User_service.DTO;

import lombok.Data;

@Data
public class ChangePasswordDTO {

    private  String currentPassword;
    private String newPassword;

}
