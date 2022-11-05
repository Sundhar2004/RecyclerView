package com.example.employee;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class EmployeeAdapterClass extends RecyclerView.Adapter<EmployeeAdapterClass.ViewHolder>{

    List<EmployeeModelClass> employee;
    Context context;
    DatabaseHelperClass databaseHelperClass;


    public EmployeeAdapterClass(List<EmployeeModelClass> employee, Context context) {
        this.employee = employee;
        this.context = context;
        databaseHelperClass = new DatabaseHelperClass(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view =layoutInflater.inflate(R.layout.employee_item_list,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final EmployeeModelClass employeeModelClass=employee.get(position);

        holder.textView_id.setText(Integer.toString(employeeModelClass.getId()));
        holder.editText_name.setText(employeeModelClass.getName());
        holder.editText_email.setText(employeeModelClass.getEmail());

        holder.button_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stringName=holder.editText_name.getText().toString();
                String stringEmail=holder.editText_email.getText().toString();

                databaseHelperClass.updateEmployee(new EmployeeModelClass(employeeModelClass.getId(),stringName,stringEmail));
                notifyDataSetChanged();
                ((Activity) context).finish();
                context.startActivity(((Activity) context).getIntent());


            }
        });
        holder.button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseHelperClass.deleteEmployee(employeeModelClass.getId());
                employee.remove(position);
                notifyDataSetChanged();

            }
        });

    }


    @Override
    public int getItemCount() {
        return employee.size();
    }

    public  class  ViewHolder extends RecyclerView.ViewHolder{
        TextView textView_id;
        EditText editText_name,editText_email;
        Button button_edit,button_delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView_id=itemView.findViewById(R.id.tid);
            editText_name=itemView.findViewById(R.id.edname);
            editText_email=itemView.findViewById(R.id.edemail);
            button_edit=itemView.findViewById(R.id.btnedit);
            button_delete=itemView.findViewById(R.id.btndelete);

        }
    }
    }

