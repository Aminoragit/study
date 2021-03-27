package com.example.study.model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchParam {

    private String account;
    private String email;
    private int page;

//    public SearchParam(){}
//    public SearchParam(String account){}
//public SearchParam(String account,String email,int page){
//    this.account=account;
//    this.email=email;
//    this.page=page;
};

//보는것처럼 동일한 메서드인데도 입력받는 값들마다 일일히 public SearchParam(){}을 생성해주는건
//생산성이 거지다 또한 getter와 setter도 일일히 generate하면 코드의 길이가 길어짐 == 헷갈림 == 생산성하락
//과 같은 문제가 발생한다. 이를 해결해주느것이 Lombok 플러그인이다.
//settings->plugins->Lombok 설치
//@Data를 적어주면 알아서 getter setter등을 자동으로 설정해줌
//그리고 모든 요소를 가지는 생성자를 추가한다고 한다면 @AllArgsConstructor가 추가된다.
//만약 Lombok을 깔아도 작동이 안된다면 Annotations Processor를 Enable해주면 작동된다.