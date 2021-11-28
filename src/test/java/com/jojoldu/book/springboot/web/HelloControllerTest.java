package com.jojoldu.book.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)  // 1
@WebMvcTest

// 2
public class HelloControllerTest  {

    @Autowired
    private MockMvc mvc; //웹 API를 테스트 할때 사용/ MockMvc클래스로 Http Get Post에 대한 API테스트 가능

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello")) // URL주소
                .andExpect(status().isOk())  // status상태가 isOk괜찮은지 ex) 404,500은 아닌지
                .andExpect(content().string(hello)); //cotent내용이 hello인지
    }

    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto")
                .param("name",name)                 // param : API테스트때 String만 허용돼서
                .param("amount", String.valueOf(amount))) //   숫자/날짜데이터도 등록할땐 문자열로 변경해야함
                .andExpect(status().isOk())              // status상태가 isOk괜찮은지 ex) 404,500은 아닌지
                .andExpect(jsonPath("$.name", is(name)))    // jsonpath: json응답값을 필드별로 검증하는 메소드
                .andExpect(jsonPath("$.amount", is(amount)));// 필드명name,amount 앞에 $써서 명시해야함
    }
}