
package com.jiwoong.billingsystem.streaming.entity;

import com.jiwoong.billingsystem.streaming.dto.ROLE;
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
@Table(name = "users", indexes = {
        @Index(name = "idx_username", columnList = "username")
})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Users extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId; // 사용자 ID

    // required parameters
    @Column(nullable = false, unique = true)
    private String username; // 사용자  고유 이름

    @Column
    private String name; // 사용자 이름

    @Column
    private String email;// 사용자 이메일

    @Column
    @Enumerated(EnumType.STRING)
    private ROLE role; // 권한(ROLE_USER/ROLE_ADMIN)

    @OneToMany(mappedBy = "uploader", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Videos> uploadVideosList = new ArrayList<>(); // 업로드 한 비디오 목록

    @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<VideoWatchHistory> videoWatchHistoryList; // 동영상 시청 기록

    @OneToMany(mappedBy = "user")
    private List<AdWatchHistory> adWatchHistoryList; // 광고 시청 기록

    private Users(String username, String name, String email, ROLE role) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public static Users createUsers(String username, String name, String email, ROLE role) {
        return new Users(username, name, email, role);
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
    public void updateExistUser(String email, String name) {
        this.name = name;
        this.email = email;
    }
}

