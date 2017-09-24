package com.example.ffes.time.usecase;

/**
 * Created by Ffes on 2017/9/23.
 */

public abstract class CallBack<Result> {
    private UiThreadExecutor uiThreadExecutor;

    public abstract void onSuccess(Result  result);
    public abstract void onError();
    public abstract void onPostExecute();

    public void setUiThreadExecutor(UiThreadExecutor uiThreadExecutor) {
        this.uiThreadExecutor = uiThreadExecutor;
    }

    protected void dispatchResult(final Result  result){
        if(uiThreadExecutor!=null){
            uiThreadExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    onPostExecute();
                    onSuccess(result);
                }
            });
        }
    }

    protected void dispatchError(){
        if(uiThreadExecutor!=null){
            uiThreadExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    onPostExecute();
                    onError();
                }
            });
        }
    }
}
