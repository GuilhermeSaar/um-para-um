package com.gsTech.um_para_um.service;

import com.gsTech.um_para_um.Repository.HolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class HolderService implements UserDetailsService {

    @Autowired
    private HolderRepository holderRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return this.holderRepository.findByEmail(email);
    }
}
