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

import com.google.common.collect.Lists;
import jakarta.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import java.util.Collections;
import java.util.List;

@Access(AccessType.FIELD)
@Entity
@Table(name = "medium", schema = "musicbrainz")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Medium {

  @Id
  @Column(name = "id")
  private int id;

//  @OneToOne(targetEntity = Release.class, optional = false, fetch = FetchType.LAZY)
//  @OneToMany(targetEntity = Release.class, optional = false, fetch = FetchType.LAZY)
//  @JoinColumn(name = "release")
//  private Release release;

  @Column(name = "position")
  private int position;

  @Column(name = "name")
  private String name;

  @Column(name = "last_updated")
  private DateTime lastUpdated;

  @OneToMany(targetEntity = Track.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "medium")
  @OrderBy("position")
  private final List<Track> tracks = Lists.newArrayList();

  public int getId() {
    return id;
  }

//  public Release getRelease() {
//    return release;
//  }

  public int getPosition() {
    return position;
  }

  public String getName() {
    return name;
  }

  public DateTime getLastUpdated() {
    return lastUpdated;
  }

  /**
   * Returns an immutable list of {@link Track}s ordered by position.
   * 
   * @return Empty list if medium has no {@link Track}s
   */
  public List<Track> getTracks() {
    return Collections.unmodifiableList(tracks);
  }

}
