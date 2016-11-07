package com.moka.framework.extenstion

import rx.Subscription
import rx.subscriptions.CompositeSubscription

fun Subscription.put(subscription: CompositeSubscription) {
    subscription.add(this)
}

fun CompositeSubscription.clear_() {
    if (!this.isUnsubscribed) {
        this.unsubscribe()
    }
}
