package BillsPaymentSystem.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "bank_accounts")
public class BankAccount extends BillingDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "bank_name", nullable = false, unique = true)
    private String bankName;

    @Column(name = "swift_code", nullable = false, unique = true)
    private String swiftCode;

    public BankAccount() {
        super();
    }

    public BankAccount(String bankName, String swiftCode) {
        this();
        this.bankName = bankName;
        this.swiftCode = swiftCode;
    }

    @Override
    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
