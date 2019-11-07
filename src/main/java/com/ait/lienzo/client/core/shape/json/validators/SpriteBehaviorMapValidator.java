/*
   Copyright (c) 2017 Ahome' Innovation Technologies. All rights reserved.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

package com.ait.lienzo.client.core.shape.json.validators;

public class SpriteBehaviorMapValidator extends AbstractAttributeTypeValidator
{
    public static final SpriteBehaviorMapValidator INSTANCE = new SpriteBehaviorMapValidator();

    public SpriteBehaviorMapValidator()
    {
        super("SpriteBehaviorMap");
    }

    @Override
    public void validate(final Object jval, final ValidationContext ctx) throws ValidationException
    {
        if (null == jval)
        {
            ctx.addBadTypeError(getTypeName());

            return;
        }
        // @FIXME serialization (mdp)
//        final JSONObject jobj = jval.isObject();
//
//        if (null == jobj)
//        {
//            ctx.addBadTypeError(getTypeName());
//        }
//        else
//        {
//            final Set<String> keys = jobj.keySet();
//
//            if (keys.isEmpty())
//            {
//                ctx.addBadTypeError(getTypeName() + ": empty behavior keys");
//
//                return;
//            }
//            for (String ikey : keys)
//            {
//                final String akey = StringOps.toTrimOrNull(ikey);
//
//                if (null == akey)
//                {
//                    ctx.addBadTypeError(getTypeName() + ": empty behavior name");
//
//                    return;
//                }
//                final JSONValue ival = jobj.get(akey);
//
//                if (null == ival)
//                {
//                    ctx.addBadTypeError(getTypeName() + ": missing behavior array for " + akey);
//
//                    return;
//                }
//                final JSONArray jarr = ival.isArray();
//
//                if (null == jarr)
//                {
//                    ctx.addBadTypeError(getTypeName() + ": invalid behavior array for " + akey);
//
//                    return;
//                }
//                if (jarr.size() < 2)
//                {
//                    ctx.addBadArraySizeError(2, jarr.size());
//
//                    return;
//                }
//                BoundingBoxArrayValidator.INSTANCE.validate(jarr, ctx);
//            }
//        }
    }
}
