package uni.social.app.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;

@MappedSuperclass
public abstract class BaseEntity<ID> {

	@Column(name = "creation_time", nullable = false)
//	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime creationTime;

	@Column(name = "modification_time", nullable = false)
//	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime modificationTime;

	@Version
	private long version;

	public abstract ID getId();

	// Other getters are omitted for the sake of clarity.

	@PrePersist
	public void prePersist() {
		LocalDateTime now = LocalDateTime.now();
		this.creationTime = now;
		this.modificationTime = now;
	}

	@PreUpdate
	public void preUpdate() {
		this.modificationTime = LocalDateTime.now();
	}
}