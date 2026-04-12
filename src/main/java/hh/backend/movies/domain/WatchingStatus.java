package hh.backend.movies.domain;

public enum WatchingStatus {
  Watched("Watched"),
  Watching("Watching"),
  Planned("Planned"),
  Dropped("Dropped");

  private final String label;

  WatchingStatus(String label) {
    this.label = label;
  }

  public String getLabel() {
    return label;
  }
}
