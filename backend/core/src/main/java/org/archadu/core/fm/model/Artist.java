/*
 * Copyright 2013 The musicbrainz-data Authors
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.archadu.core.fm.model;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import jakarta.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

import java.util.Set;
import java.util.UUID;

@Access(AccessType.FIELD)
@Entity
@Table(name = "artist", schema = "musicbrainz")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Artist extends AbstractCoreEntity {

  @ElementCollection(fetch = FetchType.LAZY)
  @CollectionTable(name = "artist_gid_redirect", schema = "musicbrainz", joinColumns = @JoinColumn(name = "new_id"))
  @Column(name = "gid")
  private final Set<UUID> redirectedGids = Sets.newHashSet();

  @Column(name = "name")
  private String name;

  @Column(name = "type")
  private ArtistType type;

  @ManyToOne(optional = true, fetch = FetchType.LAZY)
  @JoinColumn(name = "area", nullable = false)
  private Area area;

  @Column(name = "gender")
  private Gender gender;

  @Embedded
  @AttributeOverrides({ @AttributeOverride(name = "year", column = @Column(name = "begin_date_year")),
    @AttributeOverride(name = "month", column = @Column(name = "begin_date_month")),
    @AttributeOverride(name = "day", column = @Column(name = "begin_date_day")) })
  private PartialDate beginDate;

  @Embedded
  @AttributeOverrides({ @AttributeOverride(name = "year", column = @Column(name = "end_date_year")),
    @AttributeOverride(name = "month", column = @Column(name = "end_date_month")),
    @AttributeOverride(name = "day", column = @Column(name = "end_date_day")) })
  private PartialDate endDate;

  @Column(name = "ended")
  private boolean ended;

  @ManyToOne(optional = true, fetch = FetchType.LAZY)
  @JoinColumn(name = "begin_area", nullable = false)
  private Area beginArea;

  @ManyToOne(optional = true, fetch = FetchType.LAZY)
  @JoinColumn(name = "end_area", nullable = false)
  private Area endArea;

  public ArtistType getType() {
    return type;
  }

  public Area getArea() {
    return area;
  }

  public Area getBeginArea() {
    return beginArea;
  }

  public Area getEndArea() {
    return endArea;
  }

  public Gender getGender() {
    return gender;
  }

  public boolean hasEnded() {
    return ended;
  }

  public String getName() {
    return name;
  }

  /**
   * Returns an immutable set of all associated GIDs (canonical and redirected).
   */
  public Set<UUID> getGids() {
    return new ImmutableSet.Builder<UUID>().addAll(redirectedGids).add(gid).build();
  }

  public PartialDate getBeginDate() {
    return beginDate;
  }

  public PartialDate getEndDate() {
    return endDate;
  }

}
