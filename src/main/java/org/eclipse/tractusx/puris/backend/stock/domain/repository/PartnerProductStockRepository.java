/*
 * Copyright (c) 2023 Volkswagen AG
 * Copyright (c) 2023 Fraunhofer-Gesellschaft zur Foerderung der angewandten Forschung e.V.
 * (represented by Fraunhofer ISST)
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
package org.eclipse.tractusx.puris.backend.stock.domain.repository;

import org.eclipse.tractusx.puris.backend.stock.domain.model.PartnerProductStock;
import org.eclipse.tractusx.puris.backend.stock.domain.model.Stock;
import org.eclipse.tractusx.puris.backend.stock.domain.model.datatype.DT_StockTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * Repository for {@link PartnerProductStock} that is a specific type of {@link Stock}.
 * <p>
 * PartnerProductStock has the following properties:
 * <li>stock.type == StockType.Product</li>
 * <li>stock.allocatedToMe == true</li>
 * <li>stock.allocatedToPartner == empty (no partners for yourself)</li>
 * <li>stock.material.orderedBy == set to a (specific) Partner with partner.actsAsCustomerFlag == true</li>
 */
public interface PartnerProductStockRepository extends JpaRepository<PartnerProductStock, UUID> {

    List<PartnerProductStock> findAllByType(DT_StockTypeEnum stockType);

    List<PartnerProductStock> findAllByMaterial_UuidAndType(UUID materialUuid, DT_StockTypeEnum stockType);

    List<PartnerProductStock> findAllByMaterial_UuidAndTypeAndSupplierPartner_Uuid(UUID materialUuid, DT_StockTypeEnum stockType, UUID supplierUuid);
}
