/*
 * Copyright (c) 2023 Volkswagen AG
 * Copyright (c) 2023 Fraunhofer-Gesellschaft zur Foerderung der angewandten Forschung e.V. (represented by Fraunhofer ISST)
 * Copyright (c) 2023 Contributors to the Eclipse Foundation
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Apache License, Version 2.0 which is available at
 * https://www.apache.org/licenses/LICENSE-2.0.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 */
package org.eclipse.tractusx.puris.backend.common.api.logic.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.eclipse.tractusx.puris.backend.common.api.domain.model.Request;
import org.eclipse.tractusx.puris.backend.common.api.domain.model.Response;
import org.eclipse.tractusx.puris.backend.common.api.domain.model.datatype.DT_RequestStateEnum;
import org.eclipse.tractusx.puris.backend.stock.logic.dto.ProductStockRequestForMaterialDto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Dto for {@link org.eclipse.tractusx.puris.backend.common.api.domain.model.Request}
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RequestDto {

    /**
     * State of the request.
     *
     * @see DT_RequestStateEnum
     */
    @NotNull
    private DT_RequestStateEnum state;

    /**
     * Technical identifier for a {@link org.eclipse.tractusx.puris.backend.common.api.domain.model.Message}.
     * <p>
     * Only set for existing entities.
     */
    @JsonIgnore
    private UUID uuid;

    /**
     * Steering information of a {@link Request} or {@link Response} api message.
     */
    @NotNull
    @JsonProperty("headers")
    private MessageHeaderDto header;

    /**
     * List of actual content of the payload.
     * <p>
     * May contain also errors.
     */
    @NotNull
    @JsonProperty("payload")
    private List<ProductStockRequestForMaterialDto> payload = new ArrayList<>();

    /**
     * Create a RequestDto from message
     *
     * @param state   state to set
     * @param message message to set
     */
    /**
     * Create a RequestDto manually
     *
     * @param state            state of the request
     * @param messageHeaderDto messageHeader of the request
     * @param payload          actual payload of the request
     */
    public RequestDto(DT_RequestStateEnum state,
                      MessageHeaderDto messageHeaderDto, List<ProductStockRequestForMaterialDto> payload) {
        this.state = state;
        this.setPayload(payload);
        this.setHeader(messageHeaderDto);
    }
}
