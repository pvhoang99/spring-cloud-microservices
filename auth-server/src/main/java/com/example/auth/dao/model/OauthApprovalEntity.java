package com.example.auth.dao.model;

import com.fasterxml.jackson.databind.ser.Serializers.Base;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Md. Amran Hossain
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "oauth_approvals")
public class OauthApprovalEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;

    private String clientId;

    private String scope;

    private String status;

    private Date expiresAt;

    private Date lastModifiedAt;
}
