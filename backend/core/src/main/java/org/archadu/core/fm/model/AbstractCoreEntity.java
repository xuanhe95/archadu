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

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import java.util.UUID;

@MappedSuperclass
public abstract class AbstractCoreEntity {

  @Id
  @Column(name = "id")
  protected int id;

  @Column(name = "gid", nullable = false, unique = true)
  protected UUID gid;

  @Column(name = "comment")
  protected String comment;

  @Column(name = "last_updated")
  protected DateTime lastUpdated;

  public int getId() {
    return id;
  }

  public String getComment() {
    return comment;
  }

  public DateTime getLastUpdated() {
    return lastUpdated;
  }

  public abstract String getName();

}
