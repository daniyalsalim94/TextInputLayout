package info.daniyalsalim.textinputlayout2;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class component extends RelativeLayout {



    public com.google.android.material.textfield.TextInputLayout til;
    public EditText et;
    public ImageView ivDropdown;
    public Button btn;

    String items;

    Context _context;

    public component(Context context, AttributeSet attrs) {
        super(context, attrs);
        _context = context;
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TextInputLayout);
        initControl(context, attrs,ta);
    }




    private void initControl(Context context, AttributeSet attrs,TypedArray ta)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.text_input_layout, this);

        til = (com.google.android.material.textfield.TextInputLayout)findViewById( R.id.til );
        et = (EditText) findViewById( R.id.et );
        btn = (Button)findViewById( R.id.btn );
        ivDropdown = (ImageView)findViewById(R.id.ivDropdown);


        String hintText  = ta.getString(R.styleable.TextInputLayout_hintText);
        boolean isButtonEnabled = ta.getBoolean(R.styleable.TextInputLayout_isButtonEnabled,false);
        boolean isPopUp = ta.getBoolean(R.styleable.TextInputLayout_isPopUp,false);
        items = ta.getString(R.styleable.TextInputLayout_itemsArr);


        til.setHint(hintText);
        et.setHint(hintText);





        if(items == null)
        {
            items = "";
        }

        if (isButtonEnabled)
        {
            et.setFocusable(false);
            et.setTextIsSelectable(true);
            til.setFocusable(false);
            et.setTextIsSelectable(true);
            et.setInputType(InputType.TYPE_NULL);
            btn.setVisibility(VISIBLE);

        }


        if(isPopUp)
        {
            et.setFocusable(false);
            et.setTextIsSelectable(true);
            til.setFocusable(false);
            et.setTextIsSelectable(true);
            et.setInputType(InputType.TYPE_NULL);
            btn.setVisibility(VISIBLE);
            ivDropdown.setVisibility(VISIBLE);
            btn.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {




                    if(!items.equals("")){
                        if(items.contains("|")) {
                            String[] itemArr = items.split("\\|");


                            PopupMenu popUp = new PopupMenu(_context, btn);

                            for (int i = 0 ; i< itemArr.length;i++)
                            {
                                popUp.getMenu().add(itemArr[i]);
                            }
                            popUp.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                                public boolean onMenuItemClick(MenuItem item) {

                                    et.setText( item.getTitle());

                                    return true;
                                }
                            });

                            popUp.show();
                        }
                    }
                    else {
                        Toast.makeText(
                                _context,
                                "Please Add List Items",
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                }
            });


        }





    }


    public void setText(String text)
    {
        et.setText(text);
    }
}
