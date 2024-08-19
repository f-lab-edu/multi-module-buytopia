package com.zeroskill.common.entity;

import com.zeroskill.common.dto.BannerDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Getter
@Table(name = "banner")
@NoArgsConstructor
public class Banner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private String linkUrl;

    @Column(nullable = false)
    private Boolean isActive;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date endDate;

    @Column(nullable = true)
    private String description;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private Admin createdBy;

    @ManyToOne
    @JoinColumn(name = "updated_by")
    private Admin updatedBy;

    public Banner(String title, String imageUrl, String linkUrl, Date startDate, Date endDate, String description, Admin creator, Admin updater) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.linkUrl = linkUrl;
        this.isActive = true;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.createdBy = creator;
        this.updatedBy = updater;
    }

    public static BannerDto of(Banner banner) {
        return new BannerDto(
                banner.getId(),
                banner.getTitle(),
                banner.getImageUrl(),
                banner.getLinkUrl(),
                banner.getIsActive(),
                banner.getStartDate(),
                banner.getEndDate(),
                banner.getDescription(),
                banner.getCreatedBy().getId(),
                banner.getUpdatedBy().getId()
        );
    }

    public static Banner toEntity(BannerDto dto, Admin creator, Admin updater) {
        return new Banner(dto.title(), dto.imageUrl(), dto.linkUrl(),  dto.startDate(), dto.endDate(), dto.description(), creator, updater);
    }
}
