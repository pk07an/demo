package com.cignacmb.example.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.cignacmb.example.web.vo.UserVo;

public class UserValidator implements Validator
{

    @Override
    public boolean supports(Class<?> clazz)
    {
        return UserVo.class.equals(clazz);
    }


    @Override
    public void validate(Object obj, Errors errors)
    {
        // TODO Auto-generated method stub
        ValidationUtils.rejectIfEmpty(errors, "name", null, "Name is empty.");
        ValidationUtils.rejectIfEmpty(errors, "password", null, "password is empty.");
        // UserVo user = (UserVo) obj;
        // if (null == user.getPassword() || "".equals(user.getPassword()))
        // errors.rejectValue("password", null, "Password is empty.");
    }

}
