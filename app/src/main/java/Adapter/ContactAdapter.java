package Adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.atul.arsenal.R;

import java.util.List;

import Utils.ContactInfo;

import static android.content.ContentValues.TAG;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
    private List<ContactInfo> contactList;

    public ContactAdapter(List<ContactInfo> list) {
        Log.d(TAG, "ContactAdapter: " + list);
        contactList = list;
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    @Override
    public void onBindViewHolder(ContactViewHolder contactViewHolder, int i) {
        ContactInfo ci = contactList.get(i);
        contactViewHolder.vName.setText(ci.name);
        contactViewHolder.vSurname.setText(ci.surname);
        contactViewHolder.vEmail.setText(ci.email);
        contactViewHolder.vTitle.setText(ci.name + " " + ci.surname);
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.card_layout, parent, false);
        return new ContactViewHolder(itemView);
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        protected TextView vName;
        protected TextView vSurname;
        protected TextView vEmail;
        protected TextView vTitle;

        public ContactViewHolder(View v) {
            super(v);
            vName =  (TextView) v.findViewById(R.id.txtName);
            vSurname = (TextView)  v.findViewById(R.id.txtSurname);
            vEmail = (TextView)  v.findViewById(R.id.txtEmail);
            vTitle = (TextView) v.findViewById(R.id.title);
        }
    }
}
