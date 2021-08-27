package com.servlet.project.model.entity;

import java.util.Objects;

public class Participant {
    private long id;
    private boolean isSpeaker;
    private String nickName;
    private long userId;
    private Long eventId;

    public Participant() {
    }

    public Participant(long id, boolean isSpeaker, String nickName, long userId, Long eventId) {
        this.id = id;
        this.isSpeaker = isSpeaker;
        this.nickName = nickName;
        this.userId = userId;
        this.eventId = eventId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public boolean isSpeaker() {
        return isSpeaker;
    }

    public void setSpeaker(boolean speaker) {
        isSpeaker = speaker;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        return id == that.id &&
                userId == that.userId &&
                isSpeaker == that.isSpeaker &&
                Objects.equals(eventId, that.eventId) &&
                Objects.equals(nickName, that.nickName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, isSpeaker, eventId, nickName);
    }

    @Override
    public String toString() {
        return "Participant{" +
                "id=" + id +
                ", userId=" + userId +
                ", isSpeaker=" + isSpeaker +
                ", eventId=" + eventId +
                ", nickName='" + nickName + '\'' +
                '}';
    }

    private Participant(ParticipantBuilder builder) {
        this.id = builder.id;
        this.userId = builder.userId;
        this.isSpeaker = builder.isSpeaker;
        this.eventId = builder.eventId;
        this.nickName = builder.nickName;
    }

    public static ParticipantBuilder builder() {
        return new ParticipantBuilder();
    }

    public static class ParticipantBuilder {
        private long id;
        private long userId;
        private boolean isSpeaker;
        private long eventId;
        private String nickName;

        public ParticipantBuilder id(long id) {
            this.id = id;
            return this;
        }

        public ParticipantBuilder userId(long userId) {
            this.userId = userId;
            return this;
        }

        public ParticipantBuilder isSpeaker(boolean isSpeaker) {
            this.isSpeaker = isSpeaker;
            return this;
        }

        public ParticipantBuilder eventId(long eventId) {
            this.eventId = eventId;
            return this;
        }

        public ParticipantBuilder nickName(String nickName) {
            this.nickName = nickName;
            return this;
        }

        public Participant build() {
            return new Participant(this);
        }
    }
}
