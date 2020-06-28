package com.payment;

import com.SpringbootApplication;
import com.entity.payment.BankCard;
import com.service.payment.BankCardService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author 冉堃赤
 * @date 2020/3/29 10:57
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {SpringbootApplication.class})
public class TestBankCard {

    @Autowired
    private BankCardService bankCardService;

    @Test
    public void testTransferAccounts() throws Exception {
        int i = bankCardService.transferAccounts("6225112233445566", "6225222233445566", 1000);
        System.out.println(i);
    }

    @Test
    public void testSelectCardByNum() throws Exception {
        BankCard bankCard = bankCardService.selectBankCardByNumber("6225112233445566");
        System.out.println(bankCard);
    }

    @Test
    public void testSelectAll() throws Exception {
        List<BankCard> bankCards = bankCardService.selectAllBankCard();
        bankCards.forEach(System.out::println);
    }
}
