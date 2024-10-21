package com.bhan.ked.service;

import com.bhan.ked.entity.SocietyMemberEntity;
import com.bhan.ked.repository.SocietyMemberRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class SocietyMemberService {

    @Inject
    private SocietyMemberRepository societyMemberRepository;

    public void saveSocietyMember(SocietyMemberEntity societyMemberEntity){
        societyMemberRepository.save(societyMemberEntity);
    }
}
