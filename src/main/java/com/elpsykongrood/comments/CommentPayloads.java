package com.elpsykongrood.comments;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.Instant;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CommentPayloads {

    public record Create(
            @NotBlank(message = "{validation.comment.nickname.required}")
            @Size(max = 24, message = "{validation.comment.nickname.size}") String nickname,
            @NotBlank(message = "{validation.comment.content.required}")
            @Size(max = 1000, message = "{validation.comment.content.size}") String content
    ) {
        public Create {
            nickname = nickname == null ? "" : nickname.strip();
            content = content == null ? "" : content.strip();
        }
    }

    public record Item(
            Long id,
            String nickname,
            String content,
            Instant createdAt
    ) {}
}
