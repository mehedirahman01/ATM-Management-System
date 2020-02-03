package com.light.atmmanagement;

import java.util.*;
import java.io.*;

public class accountsInfo 
{
	private String AccountNo;
	private String name;
	private String Deposit;
        private accountsInfo ob;
	private int index;
	
	public accountsInfo(String AccountNo,String name,String Deposit)
	{
		this.AccountNo = AccountNo;
		this.name = name;
		this.Deposit = Deposit;
	}
        
        public accountsInfo(accountsInfo ob, int index)
	{
		this.ob = ob;
		this.index = index;
	}
        
        public accountsInfo(){
            
        }

	public String getAccountNo() {
		return AccountNo;
	}

	public void setAccountNo(String accountNo) {
		AccountNo = accountNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getDeposit() {
		return Deposit;
	}

	public void setDeposit(String deposit) {
		Deposit = deposit;
	}	
	
}


