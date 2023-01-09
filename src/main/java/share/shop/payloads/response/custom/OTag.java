package share.shop.payloads.response.custom;

import lombok.*;

import java.util.Objects;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OTag {
    private Long tagId;
    private String tagName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OTag oTag = (OTag) o;
        return Objects.equals(tagId, oTag.tagId) && Objects.equals(tagName, oTag.tagName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tagId, tagName);
    }
}
