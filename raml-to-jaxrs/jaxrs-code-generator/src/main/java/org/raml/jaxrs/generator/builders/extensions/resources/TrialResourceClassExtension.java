/*
 * Copyright ${licenseYear} (c) MuleSoft, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 */
package org.raml.jaxrs.generator.builders.extensions.resources;

import com.squareup.javapoet.TypeSpec;
import org.raml.jaxrs.generator.extension.Context;
import org.raml.jaxrs.generator.extension.resources.ResourceClassExtension;
import org.raml.jaxrs.generator.ramltypes.GResource;
import org.raml.jaxrs.generator.v10.V10GResource;

/**
 * Created by Jean-Philippe Belanger on 1/6/17. Just potential zeroes and ones
 */
public class TrialResourceClassExtension implements ResourceClassExtension<GResource> {

  @Override
  public TypeSpec.Builder onResource(Context context, GResource resource, TypeSpec.Builder typeSpec) {

    return typeSpec;
  }
}
