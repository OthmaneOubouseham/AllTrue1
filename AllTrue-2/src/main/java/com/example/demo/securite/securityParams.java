package com.example.demo.securite;

public interface securityParams {
	public static final String JWTheaderName="Authorization";
	public static final String SECRET="oth@oubou.net";
	public static final long EXPIRATION=10*24*3600*1000;
	public static final String HEADER_PREFIX="bearer ";
}
