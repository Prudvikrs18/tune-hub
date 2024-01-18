package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entities.Users;
import com.example.demo.repositories.UsersRepository;
@Service
public class UsersServiceImplementation implements UsersService{
	@Autowired
	UsersRepository rapo;

	@Override
	public String addUser(Users user) {
		rapo.save(user);
		return "User add succuesfully";
	}
	public boolean emailExists(String email) {
		if(rapo.findByEmail(email)==null)
		{
			return false;
		}
		else
		{
			return true;
		}
		
	}
	
	public boolean validateUser(String email, String password) {
		Users user=rapo.findByEmail(email);
		String db_pass=user.getPassword();
		if(password.equals(db_pass))
		{
			return true;
		}
		else
		{
		return false;
		}
	}
	@Override
	public String getRole(String email) {
		// TODO Auto-generated method stub
		Users user=rapo.findByEmail(email);
		
		return user.getRole();
	}
	@Override
	public Users getUser(String email)
	{
		return rapo.findByEmail(email);
		
	}
	@Override
	public void updateUser(Users user)
	{
		rapo.save(user);
	}
	

}
