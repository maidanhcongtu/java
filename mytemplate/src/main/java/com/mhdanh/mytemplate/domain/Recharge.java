package com.mhdanh.mytemplate.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "history_recharge")
public class Recharge {

	public static enum RechargeType {
		CARD
	}

	public static enum CardType {
		VIETEL, MOBI, VINA, GATE, VTC, VNM
	}

	@Id
	@GenericGenerator(name = "increment", strategy = "increment")
	@GeneratedValue(generator = "increment")
	@Column(name = "id")
	private Integer id;

	@Column
	private Integer money;

	@Column
	private Date date;

	@Column
	private RechargeType type;

	@Column
	private CardType cardType;

	@Column
	private String transactionId;

	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account recharger;

	public Recharge() {
	}

	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		if (!(o instanceof Recharge)) {
			return false;
		}
		Recharge otherHistoryRecharge = (Recharge) o;
		return this.getId() == otherHistoryRecharge.getId();
	}

	@Override
	public int hashCode() {
		return this.getId();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public RechargeType getType() {
		return type;
	}

	public void setType(RechargeType type) {
		this.type = type;
	}

	public Account getRecharger() {
		return recharger;
	}

	public void setRecharger(Account recharger) {
		this.recharger = recharger;
	}

	public CardType getCardType() {
		return cardType;
	}

	public void setCardType(CardType cardType) {
		this.cardType = cardType;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

}
