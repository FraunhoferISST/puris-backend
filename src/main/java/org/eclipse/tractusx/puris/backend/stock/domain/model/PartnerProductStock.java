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
package org.eclipse.tractusx.puris.backend.stock.domain.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.eclipse.tractusx.puris.backend.masterdata.domain.model.Material;
import org.eclipse.tractusx.puris.backend.masterdata.domain.model.Partner;
import org.eclipse.tractusx.puris.backend.stock.domain.model.datatype.DT_StockTypeEnum;

import java.util.Date;

@Entity
@DiscriminatorValue("PartnerProductStock")
@Getter
@ToString(callSuper = true)
@NoArgsConstructor
public class PartnerProductStock extends Stock {

    @ManyToOne
    @JoinColumn(name = "supplier_partner_uuid")
    @NotNull
    private Partner supplierPartner;

    public PartnerProductStock(Material material, double quantity, String atSiteBpnl,
                               Date lastUpdatedOn, Partner supplierPartner) {
        super(material, quantity, atSiteBpnl, lastUpdatedOn);
        super.setType(DT_StockTypeEnum.PRODUCT);
        this.setSupplierPartner(supplierPartner);
    }

    public PartnerProductStock(Material material, double quantity, String atSiteBpnl, Date lastUpdatedOn) {
        super(material, quantity, atSiteBpnl, lastUpdatedOn);
        super.setType(DT_StockTypeEnum.PRODUCT);
    }

    public void setSupplierPartner(Partner supplierPartner) {
        this.supplierPartner = supplierPartner;
        supplierPartner.getPartnerProductStocks().add(this);
    }
}