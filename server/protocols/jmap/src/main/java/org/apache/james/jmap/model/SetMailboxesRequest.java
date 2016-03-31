/****************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one   *
 * or more contributor license agreements.  See the NOTICE file *
 * distributed with this work for additional information        *
 * regarding copyright ownership.  The ASF licenses this file   *
 * to you under the Apache License, Version 2.0 (the            *
 * "License"); you may not use this file except in compliance   *
 * with the License.  You may obtain a copy of the License at   *
 *                                                              *
 *   http://www.apache.org/licenses/LICENSE-2.0                 *
 *                                                              *
 * Unless required by applicable law or agreed to in writing,   *
 * software distributed under the License is distributed on an  *
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY       *
 * KIND, either express or implied.  See the License for the    *
 * specific language governing permissions and limitations      *
 * under the License.                                           *
 ****************************************************************/

package org.apache.james.jmap.model;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.NotImplementedException;
import org.apache.james.jmap.methods.JmapRequest;
import org.apache.james.jmap.model.mailbox.Mailbox;
import org.apache.james.jmap.model.mailbox.MailboxRequest;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

@JsonDeserialize(builder = SetMailboxesRequest.Builder.class)
public class SetMailboxesRequest implements JmapRequest {

    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder {

        private final ImmutableMap.Builder<MailboxCreationId, MailboxRequest> create;
        private final ImmutableList.Builder<String> destroy;

        private Builder() {
            create = ImmutableMap.builder();
            destroy = ImmutableList.builder();
        }

        public Builder create(Map<MailboxCreationId, MailboxRequest> requests) {
            create.putAll(requests);
            return this;
        }

        public Builder create(MailboxCreationId creationId, MailboxRequest mailbox) {
            create.put(creationId, mailbox);
            return this;
        }
        
        public Builder accountId(String accountId) {
            throw new NotImplementedException();
        }
        
        public Builder ifInState(String state) {
            throw new NotImplementedException();
        }
        
        public Builder update(Map<String, Mailbox> updates) {
            throw new NotImplementedException();
        }
        
        public Builder destroy(List<String> deletions) {
            destroy.addAll(deletions);
            return this;
        }

        public SetMailboxesRequest build() {
            return new SetMailboxesRequest(create.build(), destroy.build());
        }
    }

    private final ImmutableMap<MailboxCreationId, MailboxRequest> create;
    private final ImmutableList<String> destroy;

    @VisibleForTesting
    SetMailboxesRequest(ImmutableMap<MailboxCreationId, MailboxRequest> create, ImmutableList<String> destroy) {
        this.create = create;
        this.destroy = destroy;
    }

    public ImmutableMap<MailboxCreationId, MailboxRequest> getCreate() {
        return create;
    }

    public ImmutableList<String> getDestroy() {
        return destroy;
    }
}