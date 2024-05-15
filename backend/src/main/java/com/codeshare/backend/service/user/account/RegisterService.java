package com.codeshare.backend.service.user.account;

import java.util.Map;

public interface RegisterService {
    public Map<String, String> register(String usermname, String password, String confirmedPassword);

}
