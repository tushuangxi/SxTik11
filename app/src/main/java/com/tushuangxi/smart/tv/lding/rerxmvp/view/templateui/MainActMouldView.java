//package com.hengyuan.smartparty.telescreen.lding.rerxmvp.view.templateui;
//
//import android.content.Context;
//import android.util.AttributeSet;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.Button;
//import android.widget.FrameLayout;
//
//import androidx.annotation.IdRes;
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//
//
//import com.hengyuan.smartparty.telescreen.R;
//
//import butterknife.BindView;
//
//public class MainActMouldView extends FrameLayout implements View.OnClickListener {
//
//    private Context context;
//    private GoCommiteListener goCommiteListener;
//
//    @BindView(R.id.go_commite)
//    Button go_commite;
//    @BindView(R.id.go_fragment)
//    Button go_fragment;
//
//    public MainActMouldView(@NonNull Context context) {
//        super(context);
//        this.context = context;
//        initView();
//    }
//
//    public MainActMouldView(@NonNull Context context, @Nullable AttributeSet attrs) {
//        super(context, attrs);
//        this.context = context;
//        initView();
//    }
//
//    public MainActMouldView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        this.context = context;
//        initView();
//    }
//
//    View view;
//    public void initView() {
//        view = LayoutInflater.from(context).inflate(R.layout.activity_main_template, this, true);
//        allListeners();
//    }
//
//    private void allListeners() {
////        go_commite.setOnClickListener(this);
//        addOnClickListeners(R.id.go_commite
//                          , R.id.go_fragment
//        );
//    }
//
//    private void addOnClickListeners(@IdRes int... ids) {
//        if (ids != null) {
//            View viewId;
//            for (int id : ids) {
//                viewId = view.findViewById(id);
//                if (viewId != null) {
//                    viewId.setOnClickListener(this);
//                }
//            }
//        }
//    }
//
//    public void setGoCommiteListener(GoCommiteListener goCommiteListener) {
//        this.goCommiteListener = goCommiteListener;
//    }
//
//    public interface GoCommiteListener {
//        void gofragment(View v);
//        void goCommite(View v);
//    }
//    @Override
//    public void onClick(View view) {
//        switch (view.getId()){
//            case R.id.go_fragment:
//                if(goCommiteListener!=null){
//                    goCommiteListener.gofragment(view);
//                }
//                break;
//            case R.id.go_commite:
//                if(goCommiteListener!=null){
//                    goCommiteListener.goCommite(view);
//                }
//                break;
//
//         default:
//        }
//    }
//}
