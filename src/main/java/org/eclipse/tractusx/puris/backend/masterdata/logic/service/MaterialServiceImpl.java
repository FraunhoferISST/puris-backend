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
package org.eclipse.tractusx.puris.backend.masterdata.logic.service;

import org.eclipse.tractusx.puris.backend.masterdata.domain.model.Material;
import org.eclipse.tractusx.puris.backend.masterdata.domain.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MaterialServiceImpl implements MaterialService {

    @Autowired
    MaterialRepository materialRepository;

    @Override
    public Material create(Material material) {
        return materialRepository.save(material);
    }

    @Override
    public Material update(Material material) {
        Optional<Material> existingMaterial =
                materialRepository.findById(material.getUuid());

        if (existingMaterial.isPresent()) {
            return existingMaterial.get();
        } else return null;
    }

    @Override
    public List<Material> findAllMaterials() {
        return materialRepository.findAllByMaterialFlagIsTrue();
    }

    @Override
    public List<Material> findAllProducts() {
        return materialRepository.findAllByProductFlagIsTrue();
    }

    @Override
    public Material findByUuid(UUID materialUuid) {
        Optional<Material> foundMaterial = materialRepository.findById(materialUuid);

        if (!foundMaterial.isPresent()) {
            return null;
        }
        return foundMaterial.get();
    }

    @Override
    public Material findMaterialByMaterialNumberCustomer(String materialNumberCustomer) {

        Optional<Material> foundMaterial =
                materialRepository.findByMaterialNumberCustomerAndMaterialFlagIsTrue(materialNumberCustomer);

        if (!foundMaterial.isPresent()) {
            return null;
        }
        return foundMaterial.get();
    }

    @Override
    public Material findProductByMaterialNumberCustomer(String materialNumberCustomer) {
        Optional<Material> foundProduct =
                materialRepository.findByMaterialNumberCustomerAndProductFlagIsTrue(materialNumberCustomer);

        if (!foundProduct.isPresent()) {
            return null;
        }
        return foundProduct.get();
    }
}
