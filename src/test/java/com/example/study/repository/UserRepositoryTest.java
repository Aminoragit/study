package com.example.study.repository;
import com.example.study.model.entity.Item;
import org.junit.Assert;
import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

public class UserRepositoryTest extends StudyApplicationTests {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void create(){
        User user =new User();
        user.setAccount("TestUser03");
        user.setEmail("TestUser03@gmail.com");
        user.setPhoneNumber("010-0003-0000");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("TestUser03");

        User newUser = userRepository.save(user);
        System.out.print("newUser:" + newUser);
    }

    @Test
    @Transactional
    public void read(){
        // select * from user where id= ?
        Optional<User> user = userRepository.findByAccount("TestUser01");

        user.ifPresent(selectUser ->{

            //전달받은 detail의 값은 List값이다.
            selectUser.getOrderDetailList().stream().forEach(detail->{
                Item item= detail.getItem();
                System.out.println(item);
            });
        });
    }

//    @Test
//    public User read(@RequestParam Long id){
//        Optional<User> user = userRepository.findById(id);
//        //ifPresent user값이 있을때만.
//       user.ifPresent(selectUser ->{
//            System.out.println("user"+selectUser);//User id가 2인 값 전부 가져오기
//           System.out.println("email"+selectUser.getEmail()); //이메일값 가져오기
//        });
//        return user.get();}
//

    @Test
    public void update(){
        //findById로 id값이 2L인 것으로 user을 설정한거라 create랑 다르게 기존에 아이디가 있는지
        //확인하고 해당 값이 있다면 있는컬럼을 기준으로 update를 한다
        //단 주의할점은 Optiona~~로 user값을 설정해도
        //user.ifPresent(selectUser->{}안에
        //selectUser.setId(3L);을 해버리면 optional에서 뭘 지정해주든 id가 3L인것을 우선처리하니
        //코딩시 주의할것
        //단, 만약 해당 아이디가 없다면 ifPresent끝에 ); 뒤쪽으로 원하는 기능을 넣을수 있다
        //우린 해당 아이디가 없다면 새로운 유저를 추가한다고 써보자

        Optional<User> user =userRepository.findById(3L);
        user.ifPresent(selectUser->{
            selectUser.setAccount("pppp");
            selectUser.setUpdatedAt(LocalDateTime.now());
            selectUser.setUpdatedBy("Update methood()");
            userRepository.save(selectUser);
        });
    }

    //@DeleteMapping("/api/user")
    //    public void delete(@RequestParam Long id){ }
    @Test
    @Transactional //SQL문 자체는 모두 실행하는데 실질적으로 DB에 save는 안되게하는 test기능
    public void delete(){
        //데이터 삭제하기
        Optional<User> user =userRepository.findById(5L);
        //반드시 user는 존재해야만 한다라는 코드 <- 통과못하면 에러발생하면서 코드가 중단됨
        Assert.assertTrue(user.isPresent());

        user.ifPresent(selectUser->{
           userRepository.delete(selectUser); });

        //데이터 있는지 확인하는곳이며 위에서 id=2인것을 삭제했으므로 여기선 삭제되었다고 나온다.
        Optional<User> deleteUser =userRepository.findById(5L);

        //deleteUser의 값은 반드시 false여야만한다.
        Assert.assertFalse(deleteUser.isPresent());

        //이거는 우리가 확인한다고 작성한 코드인데 쓸데없이 길다//그래서 위에처럼 Assert를 사용함
       if(deleteUser.isPresent()){
            System.out.println("데이터 존재 : "+deleteUser.get());

        System.out.println("데이터 삭제/데이터 없음 ");
        }
   }
}
