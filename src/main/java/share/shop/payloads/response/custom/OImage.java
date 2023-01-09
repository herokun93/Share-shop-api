package share.shop.payloads.response.custom;

import lombok.*;

import java.util.Objects;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OImage {
    private Long imageId;
    private String imageUrlSmall;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OImage oImage = (OImage) o;
        return Objects.equals(imageId, oImage.imageId) && Objects.equals(imageUrlSmall, oImage.imageUrlSmall);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imageId, imageUrlSmall);
    }
}
