package com.el7eita.cashisking.rest.domain.requestDTOSs;
import java.io.Serializable;

import lombok.Data;

@Data
public class AuthenticationRequest implements Serializable {

	private static final long serialVersionUID = 7872760802664041757L;
	private String username;
    private String password;

}
