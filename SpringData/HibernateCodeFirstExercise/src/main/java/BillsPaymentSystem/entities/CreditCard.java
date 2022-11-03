package BillsPaymentSystem.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "credit_cards")
public class CreditCard extends BillingDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "card_type", nullable = false)
    private String cardType;

    @Column(name = "expiration_month", nullable = false)
    private String expirationMonth;

    @Column(name = "expiration_year", nullable = false)
    private String expirationYear;

    public CreditCard() {
        super();
    }

    public CreditCard(String cardType, String expirationMonth, String expirationYear) {
        this();
        this.cardType = cardType;
        this.expirationMonth = expirationMonth;
        this.expirationYear = expirationYear;
    }

    @Override
    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getExpirationMonth() {
        return expirationMonth;
    }

    public void setExpirationMonth(String expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    public String getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(String expirationYear) {
        this.expirationYear = expirationYear;
    }
}
