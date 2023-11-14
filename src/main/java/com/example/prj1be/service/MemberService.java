package com.example.prj1be.service;


import com.example.prj1be.domain.Member;
import com.example.prj1be.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class MemberService {

    private final MemberMapper mapper;

    public boolean add(Member member) {
        return mapper.insert(member) ==1 ;

    }


    public String getId(String id) {
        return mapper.selectId(id);
    }

    public String getEmail(String email) {
        return mapper.selectEmail(email);
    }



    public boolean validate(Member member) {
        if (member ==null) {
            return false;
        }

        if(member.getEmail().isBlank()) {
            return false;
        }

        if(member.getPassword().isBlank()) {
            return false;
        }

        if(member.getId().isBlank()) {
            return false;
        }
        return true;
    }

    public List<Member> list() {
        return mapper.selectAll();
    }

    public Member getMember(String id) {
        return mapper.selectById(id);
    }

    public boolean deleteMember(String id) {
        return mapper.deleteById(id) ==1;
    }

    public boolean update(Member member) {
//        Member oldMember = mapper.selectById(member.getId());
//        if (member.getPassword().equals("")) {
//            member.setPassword(oldMember.getPassword());
//        }
        return mapper.update(member) ==1;
    }

    public String getNickName(String nickName) {
        return mapper.selectNickName(nickName);
    }


    public boolean login(Member member) {
        Member dbMember = mapper.selectById(member.getId());

        //가져온 값이 널이 아니고 얻어온 패스워드가 member의 패스워드와 같으면 로그인 성공
        if (dbMember != null) {
            if ( dbMember.getPassword().equals(member.getPassword())) {
                return true;
            }
        }
        return false;
    }
}
