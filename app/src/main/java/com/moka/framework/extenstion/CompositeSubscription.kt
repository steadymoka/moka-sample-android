package com.moka.framework.extenstion

import rx.subscriptions.CompositeSubscription

fun CompositeSubscription.clear_() {
    if (!this.isUnsubscribed) {
        this.unsubscribe()
    }
}