package com.gsTech.um_para_um.Repository;

import com.gsTech.um_para_um.orm.Holder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface HolderRepository extends JpaRepository<Holder, Long> {

    UserDetails findByEmail(String email);

}
