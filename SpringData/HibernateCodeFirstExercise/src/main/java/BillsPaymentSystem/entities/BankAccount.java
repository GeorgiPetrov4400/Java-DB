package BillsPaymentSystem.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "bank_accounts")
public class BankAccount extends BillingDetail {

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "swift_code")
    private String swiftCode;

    public BankAccount() {
        super();
    }

    public BankAccount(String bankName, String swiftCode) {
        this();
        this.bankName = bankName;
        this.swiftCode = swiftCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getSwiftCode() {
        return swiftCode;
    }

    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
    }
}
