package com.project3.revtech.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project3.revtech.dao.WishListProductUserRepository;
import com.project3.revtech.entity.WishListEntity;
import com.project3.revtech.entity.WishListItemEntity;
import com.project3.revtech.joinedpojo.SentEmailsPojo;
import com.project3.revtech.pojo.DiscountPojo;





@Service
@Transactional
public class EmailDiscountServiceImpl {
	
	@Autowired        //will re qutowire later
	EmailService emailService;
	
	// are we taking abbrieviations?
	@Autowired
	WishListProductUserRepository wlpuRepository;
	
	@Autowired
	List<SentEmailsPojo> sentEmails;
	
	@Autowired
	SentEmailsPojo sent;
	
	// todo add custom exceptions
	public List<SentEmailsPojo> sendByDiscount(DiscountPojo discount) {
		
		//temporary
		List<WishListEntity> wishedDiscounts = new LinkedList<WishListEntity>();
			
		for(WishListEntity wishListEntity : wishedDiscounts)
		{
			// stops sentEmails from having a dooped entry
			sentEmails.clear();
			String messageText = "item" + "Just went on Sale \n"
			+ "GET " + discount.getDiscountPercentage().multiply(new BigDecimal(100)) + "% OFF!!!!!";			
			// not sure if necessary
			try {
				emailService.sendMessage(wishListEntity.getUserEntity().getEmail(),"Discount",messageText);
				sent.setProductId(1);
				sent.setDiscountId(discount.getDiscountId());
				sent.setUserId(wishListEntity.getUserId());
				sent.setEmail(wishListEntity.getUserEntity().getEmail());
				sent.setDiscount(discount.getDiscountPercentage());
				sent.setProductName("name");
				sentEmails.add(sent);
				
				
			} catch(Exception e) {
				sent.setProductId(0);
				sent.setDiscountId(discount.getDiscountId());
				sent.setUserId(wishListEntity.getUserId());
				sent.setEmail(wishListEntity.getUserEntity().getEmail());
				sent.setDiscount(discount.getDiscountPercentage());
				sent.setProductName("Message failed");
				sentEmails.add(sent);
				e.printStackTrace();			
			}
		}
		
		
		
		return sentEmails;
	}
	
	
	
	// awaiting joined bundle pojo
	public void sendByBundle(int bundleID) {
		
	}

}
