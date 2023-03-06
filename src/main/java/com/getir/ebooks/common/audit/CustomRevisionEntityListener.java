package com.getir.ebooks.common.audit;

import com.getir.ebooks.security.model.CustomUserDetails;
import org.hibernate.envers.RevisionListener;
import org.springframework.security.core.context.SecurityContextHolder;

public class CustomRevisionEntityListener implements RevisionListener {
    public void newRevision(Object revisionEntity) {
        CustomRevisionEntity customRevisionEntity = (CustomRevisionEntity) revisionEntity;
        String username =
                ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                        .getUser()
                        .getUsername();
        customRevisionEntity.setUserAccount(username);
    }
}
