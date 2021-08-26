package com.servlet.project.model.entity;

import java.util.Objects;

public class Topic {
    private long id;
    private String title;
    private long eventId;
    private long speakerId;

    public Topic() {
    }

    public Topic(long id, String title, long eventId, long speakerId) {
        this.id = id;
        this.title = title;
        this.eventId = eventId;
        this.speakerId = speakerId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public long getSpeakerId() {
        return speakerId;
    }

    public void setSpeakerId(long speakerId) {
        this.speakerId = speakerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Topic topic = (Topic) o;
        return id == topic.id &&
                eventId == topic.eventId &&
                speakerId == topic.speakerId &&
                Objects.equals(title, topic.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, eventId, speakerId);
    }

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", eventId=" + eventId +
                ", speakerId=" + speakerId +
                '}';
    }

    private Topic(TopicBuilder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.eventId = builder.eventId;
        this.speakerId = builder.speakerId;
    }

    public static TopicBuilder builder() {
        return new TopicBuilder();
    }

    public static class TopicBuilder {
        private long id;
        private String title;
        private long eventId;
        private long speakerId;

        public TopicBuilder id(long id) {
            this.id = id;
            return this;
        }

        public TopicBuilder title(String title) {
            this.title = title;
            return this;
        }

        public TopicBuilder eventId(long eventId) {
            this.eventId = eventId;
            return this;
        }

        public TopicBuilder speakerId(long speakerId) {
            this.speakerId = speakerId;
            return this;
        }

        public Topic build() {
            return new Topic(this);
        }
    }
}
