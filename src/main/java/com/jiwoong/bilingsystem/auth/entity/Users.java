
package com.jiwoong.bilingsystem.auth.entity;

import com.jiwoong.bilingsystem.auth.dto.ROLE;
import com.jiwoong.bilingsystem.global.entity.BaseEntity;
import com.jiwoong.bilingsystem.streaming.entity.UserVideoAdsHistory;
import com.jiwoong.bilingsystem.streaming.entity.UserVideoHistory;
import com.jiwoong.bilingsystem.streaming.entity.Videos;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Users extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 사용자 ID

    // required parameters
    @Column
    private String username; // 사용자  고유 이름

    @Column
    private String name; // 사용자 이름

    @Column
    private String email;// 사용자 이메일

    @Column(name = "ip_address")
    private String ipAddress = " ";

    @Column
    @Enumerated(EnumType.STRING)
    private ROLE role; // 권한(ROLE_USER/ROLE_ADMIN)

    @OneToMany(mappedBy = "uploader", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Videos> uploadVideoList = new ArrayList<>(); // 사용자 가 판매자 권한으로 업로드한 동영상 목록

    @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<UserVideoHistory> userWatchHistoryList = new ArrayList<>(); // 사용자 가 시청한 동영상 목록

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserVideoAdsHistory> userVideoAdsHistoryList = new ArrayList<>(); // 사용자 가 시청한 동영상 광고 목록


    private Users(String username, String name, String email, String ipAddress, ROLE role) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.ipAddress = ipAddress;
        this.role = role;
    }

    public static Users createUsers(String username, String name, String email, String ipAddress, ROLE role) {
        return new Users(username, name, email, ipAddress, role);
    }

    // dto->entity 변환 메서드
    public static Users toEntity(String username, String name, String email, ROLE role) {
        Users user = new Users();
        user.username = username;
        user.name = name;
        user.email = email;
        user.role = role;
        return user;
    }

    // 소셜 로그인 유저 정보 업데이트 메서드
    public void updateExistUser(String email, String name,String ipAddress) {
        this.name = name;
        this.email = email;
        this.ipAddress = ipAddress;
    }

}

