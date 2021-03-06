/**
 * Copyright 2013 Joan Zapata
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ganke.adapter;

import android.content.Context;

import java.util.List;

/**
 * Same as QuickAdapter, but adds an "itemChanged" boolean in the
 * convert() method params, which allows you to know if you are
 * adapting the new view to the same item or not, and therefore
 * make a difference between dataset changed / dataset invalidated.
 * <p/>
 * Abstraction class of a BaseAdapter in which you only need
 * to provide the convert() implementation.<br/>
 * Using the provided BaseAdapterHelper, your code is minimalist.
 * @param <T> The type of the items in the list.
 */
public abstract class EnhancedQuickAdapter<T> extends QuickAdapter<T> {

    public EnhancedQuickAdapter(Context context, int layoutResId) {
        super(context, layoutResId);
    }

    public EnhancedQuickAdapter(Context context, int layoutResId, List<T> datas) {
        super(context, layoutResId, datas);
    }

    @Override
    protected final void convert(BaseAdapterHelper helper, T item, int position) {
        boolean itemChanged = helper.associatedObject == null || !helper.associatedObject.equals(item);
        helper.associatedObject = item;
        convert(helper, item, position, itemChanged);
    }

    /**
     * @param helper      The helper to use to adapt the view.
     * @param item        The item you should adapt the view to.
     * @param itemChanged Whether or not the helper was bound to another object before.
     */
    protected abstract void convert(BaseAdapterHelper helper, T item, int position, boolean itemChanged);
}
