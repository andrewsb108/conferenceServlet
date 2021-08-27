package com.servlet.project.model.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Event {
    private long id;
    private String title;
    private LocalDateTime scheduledDate;

    public Event() {
    }

    public Event(long id, String title, LocalDateTime scheduledDate) {
        this.id = id;
        this.title = title;
        this.scheduledDate = scheduledDate;
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

    public LocalDateTime getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(LocalDateTime scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return id == event.id &&
                Objects.equals(title, event.title) &&
                Objects.equals(scheduledDate, event.scheduledDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, scheduledDate);
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", scheduledDate=" + scheduledDate +
                '}';
    }

    private Event(EventBuilder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.scheduledDate = builder.scheduledDate;
    }

    public static EventBuilder builder() {
        return new EventBuilder();
    }

    public static class EventBuilder {
        private long id;
        private String title;
        private LocalDateTime scheduledDate;

        public EventBuilder id(long id) {
            this.id = id;
            return this;
        }

        public EventBuilder title(String title) {
            this.title = title;
            return this;
        }

        public EventBuilder scheduledDate(LocalDateTime scheduledDate) {
            this.scheduledDate = scheduledDate;
            return this;
        }

        public Event build() {
            return new Event(this);
        }
    }

}
