package com.zopim.sample.chatapi.chat.log.items;

import androidx.recyclerview.widget.RecyclerView;
import android.widget.TextView;

import com.zopim.android.sdk.model.Agent;
import com.zopim.android.sdk.model.items.AgentAttachment;
import com.zopim.android.sdk.model.items.AgentMessage;
import com.zopim.android.sdk.model.items.RowItem;
import com.zopim.sample.chatapi.R;

/**
 * Class used to display a {@link AgentMessage} as an item in a {@link RecyclerView}.
 * <p>
 * Responsible for binding values to inflated views and to determine if the item needs to be updated.
 */
class AgentMessageWrapper extends ViewHolderWrapper<AgentMessage> {

    private final Agent agent;

    AgentMessageWrapper(final String messageId, final AgentMessage rowItem, final Agent agent) {
        super(ItemType.AGENT_MESSAGE, messageId, rowItem);
        this.agent = agent;
    }

    @Override
    public void bind(final RecyclerView.ViewHolder holder) {
        BinderHelper.displayTimeStamp(holder.itemView, getRowItem());
        BinderHelper.displayAgentAvatar(holder.itemView, agent);

        final TextView textView = (TextView) holder.itemView.findViewById(R.id.chat_log_agent_message_textview);
        textView.setText(getRowItem().getMessage());
    }

    @Override
    public boolean isUpdated(final RowItem rowItem) {
        return false;
    }
}