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

import jakarta.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Access(AccessType.FIELD)
@Entity
@IdClass(ArtistCreditNameCompositeKey.class)
@Table(name = "artist_credit_name", schema = "musicbrainz")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ArtistCreditName {

  @Id
  @Column(name = "artist_credit")
  private int artistCredit;

  @Id
  @Column(name = "position")
  private short position;

  @ManyToOne(targetEntity = Artist.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "artist")
  private Artist artist;

  @Column(name = "join_phrase")
  private String joinPhrase;

  public int getArtistCreditId() {
    return artistCredit;
  }

  public short getPosition() {
    return position;
  }

  public Artist getArtist() {
    return artist;
  }

  public String getName() {
    return artist.getName();
  }

  public String getJoinPhrase() {
    return joinPhrase;
  }

}
